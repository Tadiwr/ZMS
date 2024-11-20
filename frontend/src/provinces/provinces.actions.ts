"use server";

import { FormNames } from "@/utils";
import { addProvinceApi } from "./provinces.data";
import { NewProvince } from "@/types";

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