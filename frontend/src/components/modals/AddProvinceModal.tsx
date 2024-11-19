"use client";

import React, { useState } from 'react'
import PrimaryButton from '../buttons/PrimaryButton';
import Modal from './Modal';
import { FormNames } from '@/utils';

export default function AddProvinceModal() {

    const [show, setShow] = useState(true);

    const toogleModal = () => {
        setShow(!show);
    }

  return (
    <>
        <PrimaryButton onClick={toogleModal} >Add Province</PrimaryButton>

        {show && 
            <Modal onClose={toogleModal} title='Add Province' >
                <form action="" className='w-full grid grid-cols-1 gap-3' >
                    <label htmlFor="">Province Name</label>
                    <input name={FormNames.Provinces.PROVINCE_NAME} placeholder='Name' />

                    <PrimaryButton formButton >Submit</PrimaryButton>
                </form>
            </Modal>
        }

    </>
  )
}
