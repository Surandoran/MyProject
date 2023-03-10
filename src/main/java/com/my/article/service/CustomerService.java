package com.my.article.service;

import com.my.article.entity.Customer;
import com.my.article.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;


    public CustomerService(@Autowired CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return customerRepository.findByEmail(email)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Customer customer) {

        return Customer.builder()
                .id(customer.getId())
                .password(customer.getPassword())
                .phone(customer.getPhone())
                .name(customer.getName())
                .email(customer.getEmail())
                .auth(customer.getAuth())
                .build();
    }

    private void validateDuplicateMember(Customer customer){
        customerRepository.findByEmail(customer.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
    }

    //회원가입
    public String save(Customer customer){
        validateDuplicateMember(customer);//중복회원검증
        customerRepository.save(customer);
        return customer.getEmail();
    }

}
