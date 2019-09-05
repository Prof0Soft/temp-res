package by.profsoft.work.service;

import by.profsoft.work.model.User;
import by.profsoft.work.repository.IUserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User details service.
 */
@Service("userDetailsService")
@Transactional
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final IUserRepository userRepository;

    /**
     * Constructor with depend.
     *
     * @param userRepository the user repository.
     */
    @Autowired
    public UserDetailsService(final IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String socialId) throws UsernameNotFoundException {
        User user = userRepository.findBySocialId(socialId)
                .orElse(userRepository.findByName(socialId));

        if (user != null) {
            List<GrantedAuthority> authorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList(user.getRole().getNameRole());

            return org.springframework.security.core.userdetails.User.withUsername(socialId)
                    .password(user.getPassword())
                    .disabled(Boolean.FALSE)
                    .accountExpired(Boolean.FALSE)
                    .credentialsExpired(Boolean.FALSE)
                    .accountLocked(Boolean.FALSE)
                    .authorities(authorities)
                    .build();
        }

        List<GrantedAuthority> authorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ANONYMUS");
        return org.springframework.security.core.userdetails.User.withUsername(StringUtils.EMPTY)
                .password(StringUtils.EMPTY)
                .disabled(Boolean.FALSE)
                .accountExpired(Boolean.FALSE)
                .credentialsExpired(Boolean.FALSE)
                .accountLocked(Boolean.FALSE)
                .authorities(authorities)
                .build();
    }
}
