import { Province } from "@/types";
import { SERVER_BASE_URL } from "@/utils";

const BASE_URL = `${SERVER_BASE_URL}/provinces`;

export async function getAllProvinces() {
    const reqUrl = `${BASE_URL}`;
    const res = await fetch(reqUrl);
    const provinces = (await res.json()) as Province[];

    return provinces;
}