"use client";

import React, { useActionState, useState } from 'react'
import PrimaryButton from '../buttons/PrimaryButton';
import Modal from './Modal';
import { FormNames } from '@/utils';
import { addProductAction } from '@/provinces/provinces.actions';
import { useRouter } from 'next/navigation';

export default function AddProvinceModal() {

    const [show, setShow] = useState(false);
    const [state, action, pending] = useActionState(addProductAction, "");
    const {refresh} = useRouter();

    const toogleModal = () => {
        setShow(!show);
    }

    const handleSubmit = (form: FormData) => {
        action(form);
        toogleModal();
        refresh();
    }

  return (
    <>
        <PrimaryButton onClick={toogleModal} >Add Province</PrimaryButton>

        {show && 
            <Modal onClose={toogleModal} title='Add Province' >
                <form action={handleSubmit} className='w-full grid grid-cols-1 gap-3 items-center justify-center ' >
                    <label htmlFor="">Province Name</label>
                    <input required name={FormNames.Provinces.PROVINCE_NAME} placeholder='Name' />

                    <PrimaryButton pending={pending} formButton >Submit</PrimaryButton>
                </form>
            </Modal>
        }

    </>
  )
}
