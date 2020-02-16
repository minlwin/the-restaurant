import { PrimaryGeneratedColumn, Entity, Column, ManyToOne, OneToMany } from "typeorm";
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
    @Column()
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
    details:SaleDetails[]
    @Column()
    paid:boolean = false
}