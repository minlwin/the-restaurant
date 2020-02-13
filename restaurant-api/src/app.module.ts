import { SalesModule } from './sales/sales.module';
import { MasterModule } from './master/master.module';
import { EmployeeModule } from './employee/employee.module';
import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';

@Module({
  imports: [
    TypeOrmModule.forRoot(),
    MasterModule, 
    EmployeeModule, 
    SalesModule, 
  ],
  controllers: [],
  providers: [],
})
export class AppModule {}
