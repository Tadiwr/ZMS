"use client";

import React, { useActionState, useState } from 'react'
import Modal from './Modal';
import { updateProvinceAction } from '@/provinces/provinces.actions';
import { Province } from '@/types';
import { FormNames } from '@/utils';
import PrimaryButton from '../buttons/PrimaryButton';
import { useRouter } from 'next/navigation';

type Props = {
    province: Province
}

export default function UpdateProvinceModal({province}: Props) {

    const [show, setShow] = useState(false);
    const [oldProvince, action, pending] = useActionState(updateProvinceAction, province)
    const {refresh} = useRouter();

    const toogleModal = () => {
        setShow(!show);
    }

    const handleSubmit = (form:FormData) => {
        action(form);
        setShow(false);
        refresh();
    }

    return (
        <>
            <span onClick={toogleModal} className=' cursor-default text-blue-400 hover:text-white hover:bg-blue-500 px-3 py-2 w-fit rounded-xl' >Edit</span>

            {show && <Modal onClose={toogleModal} title='Update Province'>
                <form action={handleSubmit} className='grid grid-cols-1 gap-3' >
                    <label htmlFor="">Province Name</label>
                    <input type="text" defaultValue={province.name} placeholder='Name' required name={FormNames.Provinces.PROVINCE_NAME} />
                    <PrimaryButton formButton pending={pending} >Submit</PrimaryButton>
                </form>
            </Modal> 
            }
        </>
    )
}
