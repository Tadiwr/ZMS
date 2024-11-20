"use server";

import { FormNames } from "@/utils";
import { addProvinceApi, updateProvinceApi } from "./provinces.data";
import { NewProvince, Province } from "@/types";

export async function addProductAction(message: string, form: FormData) {
    try {
        // extract form
        const province: NewProvince = {
            name: form.get(FormNames.Provinces.PROVINCE_NAME)?.valueOf().toString() ?? ""
        } 

        // send api req
        await addProvinceApi(province);

        return message;

        // reload page somewhere
    } catch (error) {
        console.log(error);
        return "Error Occured";
    }
}

export async function updateProvinceAction(oldProvince: Province, form: FormData) {

    const formProvince = {
        name: form.get(FormNames.Provinces.PROVINCE_NAME)?.valueOf().toString() ?? ""
    }

    const newProvince: Province = {
        name: formProvince.name,
        id: oldProvince.id
    }

    // send api req
    return await updateProvinceApi(newProvince);
}