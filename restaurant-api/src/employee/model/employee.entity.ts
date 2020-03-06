import { PrimaryGeneratedColumn, Entity, Column, CreateDateColumn, UpdateDateColumn, BeforeInsert } from "typeorm"
import { IdEnable } from "src/common/id.enable"
import { Transform } from "class-transformer"
import moment = require("moment")
import bcrypt = require("bcrypt")

@Entity()
export class Employee implements IdEnable{
    
    @PrimaryGeneratedColumn()
    id:number
    @Column()
    name:string
    @Column()
    role:string
    @Column({nullable: false, unique: true})
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

    @BeforeInsert()
    async beforeInsert() {
        this.password = await bcrypt.hash(this.password, 10)
    }
} 