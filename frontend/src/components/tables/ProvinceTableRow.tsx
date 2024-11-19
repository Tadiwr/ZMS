import { Province } from '@/types'
import React from 'react'

type Props = {
    province: Province
}

export default function ProvinceTableRow({province}: Props) {
  return (
    <tr >
        <td>{province.id}</td>
        <td>{province.name}</td>
        <td className='cursor-default' >
            <span className='text-red-400  hover:text-white px-3 py-2 w-fit rounded-xl hover:bg-red-500' >Delete</span>
        </td>
        <td>
            <span className=' cursor-default text-blue-400 hover:text-white hover:bg-blue-500 px-3 py-2 w-fit rounded-xl' >Edit</span>
        </td>
    </tr>
  )
}
