import { PrimaryGeneratedColumn, Entity, Column, ManyToOne, OneToMany, CreateDateColumn, JoinTable } from "typeorm";
import { SaleDetails } from "./saledetails.entity";
import { Tables } from "src/master/model/tables.entity";
import { IdEnable } from "src/common/id.enable";
import { Transform } from "class-transformer"
import moment = require("moment")

@Entity()
export class Sale implements IdEnable {
    @PrimaryGeneratedColumn()
    id:number
    @ManyToOne(type => Tables, {
        eager: true,
        nullable: false
    })
    tables:Tables
    @CreateDateColumn()
    @Transform(d => moment(d).format())
    saleDate:Date
    @Column()
    subTotal:number
    @Column()
    tax:number
    @OneToMany(type => SaleDetails, detail => detail.sale, {
        cascade: true,
        eager: true
    })
    @JoinTable()
    details:SaleDetails[]
    @Column({nullable : true})
    status?:string
}