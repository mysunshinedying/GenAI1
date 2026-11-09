"use client";

import {useContext, useState} from "react";
import Link from "next/link";
import {memberLogin} from "@/apis/memberApi";
import {useRouter} from "next/navigation";
import {AuthContext} from "@/contexts/AuthContext";

export default function Exam03MemberLogin() {
  const authContext = useContext(AuthContext);

  const [loginForm, setLoginForm] = useState({
    memberId: "",
    password: "",
  });
  const router = useRouter();

  const [errors, setErrors] = useState( {});

  function validateMemberId(value) {
    return value.trim() ? "" : "아이디를 입력해 주세요.";
  }

  function validatePassword(value) {
    return value.trim() ? "" : "비밀번호를 입력해 주세요.";
  }

  const handleChange = (event) => {
    const { name, value } = event.target;
    setLoginForm((previous) => ({ ...previous, [name]: value }));
    setErrors((previous) => ({
      ...previous,
      [name]: name === "memberId" ? validateMemberId(value) : validatePassword(value),
    }));
  };

  const handleLogin = async () => {
    const nextErrors = {
      memberId: validateMemberId(loginForm.memberId),
      password: validatePassword(loginForm.password),
    };

    setErrors(nextErrors);

    const hasError = Object.values(nextErrors).some((message) => message !== "");
    if (hasError) {
      return;
    }

    // 백엔드 API 호출
    try {
       const response = await memberLogin(loginForm);
       if(response.data.status === "success") {

         authContext.setMemberId(response.data.memberId);
         authContext.setAccessToken(response.data.jwt);
         localStorage.setItem("memberId", response.data.memberId);
         localStorage.setItem("accessToken", response.data.jwt);

         

         router.push("/");
       }
    } catch (error) {
      console.error("로그인 실패:", error);
    }
  };

  return (
    <section className="w-full rounded-lg border border-zinc-200 bg-white text-zinc-900 shadow-sm dark:border-zinc-700 dark:bg-zinc-900 dark:text-zinc-100">
      <header className="rounded-t-lg border-b border-zinc-200 bg-zinc-100 px-4 py-1 dark:border-zinc-700 dark:bg-zinc-800">
        <h2 className="font-semibold">Exam03MemberLogin</h2>
      </header>
      <div className="p-4">
        <div className="max-w-md space-y-4">
          <label className="block text-sm font-medium" htmlFor="memberId">
            아이디
            <input
              id="memberId"
              name="memberId"
              value={loginForm.memberId}
              onChange={handleChange}
              aria-invalid={Boolean(errors.memberId)}
              aria-describedby={errors.memberId ? "memberId-error" : undefined}
              type="text"
              autoComplete="username"
              className="mt-1 block w-full rounded border border-zinc-300 px-3 py-2 focus:outline-2 focus:outline-orange-500 dark:border-zinc-600 dark:bg-zinc-800"
            />
            {errors.memberId && <span id="memberId-error" role="alert" className="mt-1 block text-sm text-red-600 dark:text-red-400">{errors.memberId}</span>}
          </label>
          <label className="block text-sm font-medium" htmlFor="password">
            비밀번호
            <input
              id="password"
              name="password"
              value={loginForm.password}
              onChange={handleChange}
              aria-invalid={Boolean(errors.password)}
              aria-describedby={errors.password ? "password-error" : undefined}
              type="password"
              autoComplete="current-password"
              className="mt-1 block w-full rounded border border-zinc-300 px-3 py-2 focus:outline-2 focus:outline-orange-500 dark:border-zinc-600 dark:bg-zinc-800"
            />
            {errors.password && <span id="password-error" role="alert" className="mt-1 block text-sm text-red-600 dark:text-red-400">{errors.password}</span>}
          </label>
          <div className="flex flex-wrap items-center gap-3">
            <button
              type="button"
              onClick={handleLogin}
              className="rounded bg-orange-500 px-3 py-1.5 text-sm text-white hover:bg-orange-600"
            >
              로그인
            </button>
            <Link href="/" className="rounded border border-zinc-300 px-3 py-1.5 text-sm hover:bg-zinc-100 dark:border-zinc-600 dark:hover:bg-zinc-800">
              메인으로
            </Link>
          </div>
          <div className="border-t border-zinc-200 pt-3 text-sm dark:border-zinc-700">
            <span className="text-zinc-600 dark:text-zinc-300">아직 회원이 아니신가요? </span>
            <Link href="/Ch08RestAPI/Exam02MemberJoin" className="text-orange-700 underline hover:text-orange-800 dark:text-orange-400">
              회원가입
            </Link>
          </div>
        </div>
      </div>
    </section>
  );
}
