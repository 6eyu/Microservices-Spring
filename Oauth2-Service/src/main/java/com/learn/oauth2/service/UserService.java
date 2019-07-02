package com.learn.oauth2.service;

import com.learn.oauth2.authentication.UserPrincipal;
import com.learn.oauth2.constant.PluginConstant;
import com.learn.oauth2.constant.SubscriptionConstant;
import com.learn.oauth2.model.dto.*;
import com.learn.oauth2.model.po.TAccount;
import com.learn.oauth2.model.po.TPlugin;
import com.learn.oauth2.model.po.TSubscription;
import com.learn.oauth2.model.po.TUser;
import com.learn.oauth2.repository.AccountRepository;
import com.learn.oauth2.repository.PluginRepository;
import com.learn.oauth2.repository.SubscriptionRepository;
import com.learn.oauth2.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.security.auth.login.AccountException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PluginRepository pluginRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {

        TUser TUser = userRepository.findByUsername(username);
        if(TUser == null){
            throw new UsernameNotFoundException("Invalid username");
        }
        UserPrincipal principal = new UserPrincipal(TUser, true, true,
                true, true, this.getAuthority(TUser)
        );
        return principal;
    }

    public UserPrincipal loadUserByEmail(String email) throws UsernameNotFoundException {

        TUser TUser = userRepository.findByEmail(email);
        if(TUser == null){
            throw new UsernameNotFoundException("Invalid email");
        }

        UserPrincipal principal = new UserPrincipal(TUser, true, true,
                true, true, this.getAuthority(TUser)
        );
        return principal;
    }

    private List getAuthority(TUser TUser) {

        List<SimpleGrantedAuthority> pluginList = new ArrayList<SimpleGrantedAuthority>();
        for(TSubscription TSubscription : TUser.getTAccount().getTSubscriptions()) {
            pluginList.add(new SimpleGrantedAuthority(TSubscription.gettPlugin().getName()));
        }
        return pluginList;
    }

    public List findAll() {
        List list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public TUser save(TUser TUser) {
        return userRepository.save(TUser);
    }

    public void delete(Integer id) {
        Optional<TUser> user = userRepository.findById(id);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        userRepository.delete(user.get());
    }

    public RequestHeaderDTO getSessionData(UserPrincipal userPrincipal, String plugin) throws AccountException {

        TAccount tAccount = accountRepository.findById(userPrincipal.getTUser().getTAccount().getId())
                .orElseThrow(() -> new AccountException("Account with id " + userPrincipal.getTUser().getTAccount().getId() + " does not exist"));

        List<TSubscription> tSubscriptionList = tAccount.getTSubscriptions();

        RequestHeaderDTO requestHeaderDTO = this.buildRequestHeader(userPrincipal, tAccount, plugin);

        return requestHeaderDTO;
    }

    private RequestHeaderDTO buildRequestHeader(UserPrincipal userPrincipal, TAccount tAccount, String pluginName) throws AccountException {

        TPlugin tPlugin = pluginRepository.findByNameAndStatusIs(pluginName, PluginConstant.PluginStatusType.ACTIVE.value())
                .orElseThrow(() -> new AccountException("Permission denied, you cannot access the resource"));

        AccountDTO account = new AccountDTO();
        BeanUtils.copyProperties(userPrincipal.getTUser().getTAccount(), account);

        TSubscription tSubscription = subscriptionRepository.findByTAccountAndTPluginAndStatusIs(tAccount, tPlugin,
                SubscriptionConstant.SubscriptionStatusType.ACTIVE.value())
                .orElseThrow(() -> new AccountException("Permission denied, you cannot access the resource"));

        UserDTO user = new UserDTO(userPrincipal.getTUser().getId(), account, userPrincipal.getTUser().getEmail(), userPrincipal.getTUser().getName());

        PluginDTO plugin = new PluginDTO();
        BeanUtils.copyProperties(tPlugin, plugin);

        SubscriptionDTO subscription = new SubscriptionDTO();
        BeanUtils.copyProperties(tSubscription, subscription);

        subscription.setAccount_id(account.getId());
        subscription.setPlugin(plugin);

        RequestHeaderDTO requestHeaderDTO = new RequestHeaderDTO();
        requestHeaderDTO.setUser(user);
        requestHeaderDTO.setSubscription(subscription);

        return requestHeaderDTO;
    }
}