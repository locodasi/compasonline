package com.example.ComprasOnline.services.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ComprasOnline.converters.UserConverter;
import com.example.ComprasOnline.entities.UserRole;
import com.example.ComprasOnline.models.UsuarioModelo;
import com.example.ComprasOnline.repositories.IUserRepository;
import com.example.ComprasOnline.repositories.IUserRoleRepository;
import com.example.ComprasOnline.services.IUserServices;

@Service("userService")
public class UserService implements UserDetailsService, IUserServices {

	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	
	@Autowired
	@Qualifier("userRoleRepository")
	private IUserRoleRepository userRoleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.example.ComprasOnline.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
		return buildUser(user, buildGrantedAuthorities(user.getUserRoles()));
	}
	
	private User buildUser(com.example.ComprasOnline.entities.User user, List<GrantedAuthority> grantedAuthorities) {
		return new User(user.getUsuario(), user.getContra(), user.isEnabled(),
						true, true, true, //accountNonExpired, credentialsNonExpired, accountNonLocked,
						grantedAuthorities);
	}
	
	private List<GrantedAuthority> buildGrantedAuthorities(Set<UserRole> userRoles) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		for(UserRole userRole: userRoles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<GrantedAuthority>(grantedAuthorities);
	}
	
	@Override
	public List<com.example.ComprasOnline.entities.User> getAll(){	
		return userRepository.findAll();	
	}
	
	
	@Override
	public UsuarioModelo insertOrUpdate(UsuarioModelo usuarioModelo) {

		com.example.ComprasOnline.entities.User usuario = userRepository.save(userConverter.modelToEntity(usuarioModelo));
		
		UsuarioModelo us = userConverter.entityToModel(usuario);
		
		UserRole entidadRol = new UserRole(0, usuario, "ROLE_USER");
		userRoleRepository.save(entidadRol);
		return us;
	}
	
	@Override
	public boolean remove(int id) {
		
		try {
			userRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public com.example.ComprasOnline.entities.User buscarUsuario(String usuario) {
		// TODO Auto-generated method stub
		return userRepository.findByUsuario(usuario);
	}
	
}