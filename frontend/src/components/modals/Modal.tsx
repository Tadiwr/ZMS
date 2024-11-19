import React, { ReactNode } from 'react'
import { twMerge } from 'tailwind-merge'

type Props = {
    onClose?:() => void,
    children?:ReactNode,
    className?: string,
    title?: string
}

export default function Modal({onClose, children, className, title}: Props) {
  return (
    <div className={twMerge('top-0 flex flex-col items-center justify-center left-0 fixed w-full h-screen bg-black bg-opacity-75', className)} >
        <div className='bg-white w-3/4 h-3/4 rounded-xl p-5' >
            <div className='flex flex-row items-center justify-start text-2xl font-bold' >
                <span onClick={onClose} className='mr-3 cursor-default font-normal hover:text-red-500' >X</span>
                <h1>{title}</h1>
            </div>


            <div className='mt-5' >
                {children}
            </div>
        </div>
    </div>
  )
}
