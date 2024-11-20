"use client";

import { Province } from '@/types'
import React from 'react'
import AsyncActionButton from '../buttons/AsyncActionButton';
import { deleteProvinceApi } from '@/provinces/provinces.data';
import UpdateProvinceModal from '../modals/UpdateProvinceModal';

type Props = {
    province: Province,
    no: number
}

export default function ProvinceTableRow({province, no}: Props) {

  const onDelete = async () => {
    await deleteProvinceApi(province);
  }

  return (
    <tr >
        <td>{no}</td>
        <td>{province.id}</td>
        <td>{province.name}</td>
        <td className='cursor-default' >
            <AsyncActionButton
              onClickPromise={onDelete}  
              className='text-red-400  hover:text-white px-3 py-2 w-fit rounded-xl hover:bg-red-500' 
            >Delete</AsyncActionButton>
        </td>
        <td>
            <UpdateProvinceModal province={province} />
        </td>
    </tr>
  )
}
