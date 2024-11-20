import AddProvinceModal from '@/components/modals/AddProvinceModal';
import ProvincesTable from '@/components/tables/ProvincesTable';
import { getAllProvincesApi } from '@/provinces/provinces.data'
import React from 'react';

export default async function Home() {

  const provinces = await getAllProvincesApi();

  return (
    <div className='p-5 w-full' >

      <div className='flex flex-row items-centet justify-start' >
        <div>
          <h1 className='font-black text-4xl' >Provinces</h1>
        </div>

        <div className='w-full flex flex-row justify-end items-center' >
          <AddProvinceModal />
        </div>
      </div>

      <ProvincesTable className='mt-5' provinces={provinces}  />
    </div>
  )
}
