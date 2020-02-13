import { Controller } from '@nestjs/common';
import { EmployeeService } from '../model/employee.service';
import { Employee } from '../model/employee.entity';
import { BaseController } from 'src/common/base.controller';

@Controller("employees")
export class EmployeeController extends BaseController<Employee> {

    constructor(service:EmployeeService) {
        super(service, '/employees')
    }

}
