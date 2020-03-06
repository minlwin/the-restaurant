import { Module } from '@nestjs/common';
import { MulterModule } from '@nestjs/platform-express';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AuthModule } from './auth/auth.module';
import { EmployeeModule } from './employee/employee.module';
import { MasterModule } from './master/master.module';
import { SalesModule } from './sales/sales.module';

@Module({
  imports: [
    AuthModule, 
    TypeOrmModule.forRoot(),
    MasterModule, 
    EmployeeModule, 
    SalesModule, 
    MulterModule
  ],

})
export class AppModule {}
