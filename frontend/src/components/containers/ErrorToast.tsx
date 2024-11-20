"use client";

import React, { useState } from 'react'

type Props = {
    message?:string
}

export default function ErrorToast({message}: Props) {

    const [show, setShow] = useState(message != undefined);

    

    return (
        <div>ErrorToast</div>
    )
}
