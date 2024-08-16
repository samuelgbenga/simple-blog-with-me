import { baseApi } from "./base";


export async function saveToken( postData, options) {
    const res = await baseApi.post(`push-notification/save`, postData, options);
    return res.data;
}