import { PrimaryGeneratedColumn, Entity, Column, CreateDateColumn, UpdateDateColumn } from "typeorm"
import { IdEnable } from "src/common/id.enable"
import { Transform } from "class-transformer"
import moment = require("moment")

@Entity()
export class Employee implements IdEnable{
    
    @PrimaryGeneratedColumn()
    id:number
    @Column()
    name:string
    @Column()
    role:string
    @Column()
    email:string
    @Column()
    phone:string
    @Column()
    password:string
    @CreateDateColumn()
    @Transform(d => moment(d).format())
    creation:Date
    @UpdateDateColumn()
    @Transform(d => moment(d).format())
    modification:Date
}