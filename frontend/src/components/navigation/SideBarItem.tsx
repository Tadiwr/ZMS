"use client";

import { usePathname } from 'next/navigation';
import React, { ReactNode } from 'react'
import { twMerge } from 'tailwind-merge';

export type TSideBarItem = {
    href: string,
    title: string
}

type Props = {
    children?: ReactNode,
    href?: string
}

export default function SideBarItem({children, href}: Props) {

    const path = usePathname();
    const isActive = path === href;

  return (
    <a className={twMerge(
        "w-full px-3 py-2 rounded-xl flex flex-row items-center bg-slate-200 text-slate-500 hover:bg-green-400 hover:text-white", 
        isActive && "bg-green-200 text-green-500",
        "cursor-default"
    )} 
    href={href} 
    >
        {children}
    </a>
  )
}
