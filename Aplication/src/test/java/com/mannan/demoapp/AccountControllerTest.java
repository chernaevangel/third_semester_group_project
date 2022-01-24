package com.mannan.demoapp;

import com.mannan.demoapp.Controller.AccountController;
import com.mannan.demoapp.Interfaces.*;
import com.mannan.demoapp.Model.AccountPackage.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
class AccountControllerTest {

	@Mock
	private IAccountManager accountManager;
	@Mock
	private IInterestManager interestManager;
	@Mock
	private IProjectManager projectManager;
	@Mock
	private IExperienceManager experienceManager;
	@Mock
	private ISkillManager skillManager;
	@Mock
	private IConnectionManager connectionManager;
	AccountController accountController;

	@BeforeEach
	void beforeEach(){
		accountController = new AccountController(
				accountManager,
				interestManager,
				projectManager,
				experienceManager,
				skillManager,
				connectionManager
		);
	}

	@Test
	void getAllAccount() throws SQLException {
		//act
		accountController.getAllAccounts();

		//assert
		verify(accountManager).getAccounts();
	}

}
