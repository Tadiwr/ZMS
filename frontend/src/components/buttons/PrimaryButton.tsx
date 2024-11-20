import React, { ReactNode } from 'react'

type Props = {
  classNames?: string,
  onClick?: () => void,
  children?: ReactNode,
  formButton?:boolean,
  pending?:boolean
}

export default function PrimaryButton({classNames, onClick, children, formButton = false, pending = false}: Props) {

  

  return (
    <button disabled={pending} type={formButton ? "submit" : "button"} onClick={onClick} className='py-2 px-3 bg-blue-500 text-white rounded-xl hover:bg-black transition-all' >
        {pending ?
          <span>Please Wait...</span>
          : children
        }
    </button>
  )
}
