package com.example.active.config;

import com.example.active.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/MyUser/register").permitAll()
                .antMatchers
                        ("/api/v1/Bill/get"
                                ,"/api/v1/Certificate/findAllByUserID/{userID}"
                                ,"/api/v1/Club/add","/api/v1/Club/update/{clubID}","/api/v1/Club/delete/{clubID}"
                                ,"/api/v1/Collage/add","/api/v1/Collage/update/{collageID}","/api/v1/Collage/delete/{collageID}"
                                ,"/api/v1/Permits/get"
                                ,"/api/v1/Sponsor/get","/api/v1/Sponsor/add","/api/v1/Sponsor/update/{sponsorID}","/api/v1/Sponsor/delete/{sponsorID}"

                        ).hasAuthority("ADMIN")

                .antMatchers
                        (
                                "/api/v1/Ads/add","/api/v1/Ads/update/{adsID}","/api/v1/Ads/delete/{adsID}"
                        ,"/api/v1/Bill/add","/api/v1/Bill/update/{billID}","/api/v1/Bill/delete/{billID}","/api/v1/Bill/updateBillStatus","/api/v1/Bill/findAllByClubID"
                        ,"/api/v1/Certificate/add","/api/v1/Certificate/update/{certificateID}","/api/v1/Certificate/delete/{certificateID}","/api/v1/Certificate/findAllByUserID/{userID}"
                                ,"/api/v1/MembershipRequest/findAllByClubIDAndStatus","/api/v1/MembershipRequest/findAllByClubIDAndStatusInProgress/{clubID}","/api/v1/MembershipRequest/RejectMembershipRequest/{membershipRequestID}","/api/v1/MembershipRequest/acceptMembershipRequest/{membershipRequestID}"
                                        ,"/api/v1/MyUser/update","/api/v1/MyUser/delete","/api/v1/MyUser/getUserByID"
                                ,"/api/v1/Permits/add","/api/v1/Permits/update/{permitsID}","/api/v1/Permits/delete/{permitsID}"
                                ,"/api/v1/Sponsor/get"
                                ,"/api/v1/Task/add","/api/v1/Task/update/{taskID}","/api/v1/Task/delete/{taskID}","/api/v1/Task/findAllByUserID/{userID}","/api/v1/Task/findAllByClubID/{clubID}","/api/v1/Task/Reject/{taskID}","/api/v1/Task/Completed/{taskID}"


                        ).hasAuthority("MEMBER")

                .antMatchers(
                        "/api/v1/Certificate/findAllByUserID/{userID}"
                        ,"/api/v1/MembershipRequest/add","/api/v1/MembershipRequest/update/{membershipRequestID}","/api/v1/MembershipRequest/delete/{membershipRequestID}"
                        ,"/api/v1/MyUser/update","/api/v1/MyUser/delete","/api/v1/MyUser/getUserByID"

                            ).hasAuthority("USER")
                .antMatchers("/api/v1/Bill/findBillByID/{billID}").hasAnyAuthority("ADMIN","MEMBER")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
                .httpBasic();
    }
}
