package com.lama.loanmanagementsystem.repository;

import com.lama.loanmanagementsystem.model.LoanType;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LoanTypeRepositoryTests {
    @Autowired
    private LoanRepository loanRepository ;
    @Autowired
    private LoanTypeRepository loanTypeRepository;

    private LoanType loanType;

}
