import { Province } from '@/types'
import React from 'react'
import ProvinceTableRow from './ProvinceTableRow'
import { twMerge } from 'tailwind-merge'

type Props = {
    provinces: Province[],
    className?:string
}

export default function ProvincesTable({provinces, className}: Props) {
  return (
    <table className={twMerge(className)}>
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th></th>
                <th></th>
            </tr>
        </thead>

        <tbody>
            {provinces.map((prov, index) => {
                return <ProvinceTableRow key={index} province={prov} />
            })}
        </tbody>
    </table>
  )
}
