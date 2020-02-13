import { PrimaryGeneratedColumn, Entity, Column, ManyToOne, OneToMany } from "typeorm";
import { SaleDetails } from "./saledetails.entity";
import { Transform } from 'class-transformer'
import moment = require("moment");
import { Tables } from "src/master/model/tables.entity";
import { IdEnable } from "src/common/id.enable";

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
    @Transform(d => moment(d).format('YYYY-MM-DD HH:mm:ss'))
    saleDate:Date
    @Column()
    subTotal:number
    @Column()
    tax:number
    @OneToMany(type => SaleDetails, detail => detail.sale, {
        onDelete: "CASCADE"
    })
    details:SaleDetails[]
    @Column()
    paid:boolean = false
}