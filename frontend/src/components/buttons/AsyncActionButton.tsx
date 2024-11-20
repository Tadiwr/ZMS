"use client";

import { usePathname, useRouter } from 'next/navigation';
import React, { ReactNode, useState } from 'react'
import { twMerge } from 'tailwind-merge';

type Props = {
    onClickPromise: () => Promise<void>,
    children?: ReactNode,
    className?:string
}

export default function AsyncActionButton({
    children, onClickPromise, className
}: Props) {

    const [pending, setPending] = useState(false);
    const {refresh, push} = useRouter();
    const path = usePathname();

    const handleClick = () => {
        setPending(true);

        onClickPromise()
        .then(() => {
            setPending(false);
            push(path)
        })
        .catch((err) => {
            const message = err as Error;
            console.log(message);
            // TODO: Show Eror Toast
        });

        setPending(false)

    }

    return (
        <button
            className={twMerge("", className)}
            onClick={handleClick}
        >
            {pending ?
                <span>Please Wait...</span>
                : children
            }
        </button>
    )
}
