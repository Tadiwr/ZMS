"use client";

import React, { useState } from 'react'
import PrimaryButton from '../buttons/PrimaryButton';
import Modal from './Modal';

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

            </Modal>
        }

    </>
  )
}
