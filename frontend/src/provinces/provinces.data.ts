import { NewProvince, Province } from "@/types";
import { SERVER_BASE_URL } from "@/utils";

const BASE_URL = `${SERVER_BASE_URL}/provinces`;

export async function getAllProvincesApi() {
    const reqUrl = `${BASE_URL}`;
    const res = await fetch(reqUrl);
    const provinces = (await res.json()) as Province[];

    return provinces;
}

export async function addProvinceApi(province: NewProvince) {
    const reqUrl = `${BASE_URL}/add`;

    const res = await fetch(reqUrl, {
        method: "POST",
        body: JSON.stringify(province),
        headers: {
            "Content-Type": "application/json"
        }
    });

    const NewProvince = (await res.json()) as Province;

    return NewProvince;
}

export async function deleteProvinceApi(province: Province) {
    const reqUrl = `${BASE_URL}/${province.id}`;

    const res = await fetch(reqUrl, {
        method: "DELETE",
    });
}

export async function updateProvinceApi(province: Province) {
    const reqUrl = `${BASE_URL}/update/${province.id}`;

    const res = await fetch(reqUrl, {
        method: "PUT",
        body: JSON.stringify(province),
        headers: {
            "Content-Type": "application/json"
        }
    });

    const NewProvince = (await res.json()) as Province;

    return NewProvince;
}