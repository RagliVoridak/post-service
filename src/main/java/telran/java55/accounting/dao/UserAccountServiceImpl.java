package telran.java55.accounting.dao;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java55.accounting.dto.RolesDto;
import telran.java55.accounting.dto.UserDto;
import telran.java55.accounting.dto.UserEditDto;
import telran.java55.accounting.dto.UserRegisterDto;
import telran.java55.accounting.model.UserAccount;
import telran.java55.accounting.service.UserAccountService;

@Service
public class UserAccountServiceImpl implements UserAccountService {
	
	@Autowired
	UserAccountRepository userAccountRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public UserDto register(UserRegisterDto userRegisterDto) {
		if (userAccountRepository.findByLogin(userRegisterDto.getLogin()).isPresent()) {
			throw new IllegalArgumentException("User already exists");
		}
		
		UserAccount user = new UserAccount(userRegisterDto.getLogin(), userRegisterDto.getPassword(),
				userRegisterDto.getFirstName(), userRegisterDto.getLastName());
		
		userAccountRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto removeUser(String login) {
		UserAccount user = findUserByLogin(login);
		userAccountRepository.delete(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto getUser(String login) {
		UserAccount user = findUserByLogin(login);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(String login, UserEditDto userEditDto) {
		UserAccount user = findUserByLogin(login);
		user.setFirstName(userEditDto.getFirstName());
		user.setLastName(userEditDto.getLastName());
		userAccountRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public RolesDto changeRolesList(String login, String role, boolean isAddRole) {
		UserAccount user = findUserByLogin(login);
		boolean changed = isAddRole ? user.addRole(role) : user.removeRole(role);
		if(!changed) {
			throw new IllegalArgumentException("Role operation failed");
		}
		userAccountRepository.save(user);
		return modelMapper.map(user, RolesDto.class);
				
	}

	@Override
	public void changePassword(String login, String newPassword) {
		UserAccount user = findUserByLogin(login);
		user.setPassword(newPassword);
		userAccountRepository.save(user);
		
	}
	
	private UserAccount findUserByLogin(String login) {
		return userAccountRepository.findByLogin(login)
				.orElseThrow(() -> new IllegalArgumentException("User not found"));
	}
	
}
