import React from 'react'
import SideBarItem, { TSideBarItem } from './SideBarItem'

const sidebarItems: TSideBarItem[] = [
    {
        title: "Provinces",
        href: "/"
    },

    {
        title: "Constituencies",
        href: ""
    },

    {
        title: "Administrative Districts",
        href: ""
    },

    {
        title: "Party Districts",
        href: ""
    },

    {
        title: "Branches",
        href: ""
    },

    {
        title: "Cells",
        href: ""
    },

    {
        title: "Members",
        href: ""
    },
]

export default function SideBar() {
  return (
    <div className='w-full flex flex-col' >

        <div className='grid p-4 grid-cols-1 w-full gap-3' >
            {sidebarItems.map((item, index) => {
                return <SideBarItem key={index} href={item.href} >{item.title}</SideBarItem>
            })}
        </div>
    </div>
  )
}
