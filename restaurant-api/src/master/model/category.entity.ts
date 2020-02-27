import { IdEnable } from "src/common/id.enable"
import { Column, Entity, Index, PrimaryGeneratedColumn } from "typeorm"

@Entity()
export class Category  implements IdEnable{
    
    @PrimaryGeneratedColumn()
    id:number

    @Column()
    type:string

    @Index({unique : true})
    @Column()
    name:string

}