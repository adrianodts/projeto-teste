package br.com.grupoabril.teste.spring.conf;

//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableWebSecurity
public class LoginSecurityConfig { //extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	@Qualifier("userDAO")
//	private UserDetailsService userDetailsService;

//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService)
//			.passwordEncoder(new BCryptPasswordEncoder());
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()		
//				.antMatchers("/clientes/listarAssinaturas/**").authenticated()
//				.antMatchers("/").permitAll()	
//				.and()
//			.formLogin().loginPage("/login").permitAll();
//			
//	}
//	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/resources/**");
//	}
	

}
