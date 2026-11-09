import api from "@/apis/axios";

export async function memberJoin(joinForm) {
    return await api.post("/database/member/join-member", joinForm);
}

export async function memberLogin(loginForm) {
    return await api.post("/database/member/login-member", loginForm);
}