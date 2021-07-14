# Employee Database MySQL

#### Table - employees_tbl
```bash
- id int
- name varchar(20)
- dept varchar(20)
- daysAttended int
```
#### Class - SalaryCalculator 
```bash
- int getGrossSalary(Employee, actualSalary)
- abstract int calculateNetSalary(Employee, actualSalary)
```

#### Class PermanentEmployeeSalaryCalculator
```bash
- monthlySalary
- providentFund //deductables
- incomeTax //deductables
- int calculateNetSalary(Employee, actualSalary)
```

#### Class ContractEmployeeSalaryCalculator
```bash
- perDayRate
- taxDeductions //deductables
- int calculateNetSalary(Employee, actualSalary)
```

#### Interface EmployeeDAO
```bash
- addEmployee(Employee e)
- deleteEmployee(int id)
- findEmployee(Employee e)
- showAllEmployees()
```

#### Class EmployeeDAOMySQLImpl

#### Interface EmployeeService
```bash
- addEmployee(Employee e)
- deleteEmployee(int id)
- findEmployee(Employee e)
- showAllEmployees()
```
# JDBC_HOMEWORK
# JDBC_HOMEWORK
