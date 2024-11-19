import React, { ReactNode } from 'react'

type Props = {
    classNames?: string,
    onClick?: () => void,
    children?: ReactNode
}

export default function PrimaryButton({classNames, onClick, children}: Props) {


  return (
    <button onClick={onClick} className='py-2 px-3 bg-blue-500 text-white rounded-xl hover:bg-black transition-all' >
        {children}
    </button>
  )
}
