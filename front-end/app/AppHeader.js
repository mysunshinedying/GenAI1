"use client";

import {useContext} from "react";
import {useRouter} from "next/navigation";
import Image from "next/image";
import Link from "next/link";
import {AuthContext} from "@/contexts/AuthContext";

export default function AppHeader() {
    const authContext = useContext(AuthContext);
    const router = useRouter();

    return (
        <div className="flex items-center gap-2 bg-black px-4 py-1 text-white">
            <Image src="/logo512.png" alt="React 로고" width={28} height={28}/>
            React
            {!authContext.isInitialized ?
                (
                    <span
                        aria-hidden="true"
                        className="ml-auto h-7 w-16 shrink-0"
                    />
                )
                : authContext.memberId ? (
                    <button
                        type="button"
                        onClick={() => {
                            authContext.setMemberId("");
                            authContext.setAccessToken("");
                            localStorage.removeItem("memberId");
                            localStorage.removeItem("accessToken");
                            router.push("/");
                        }}
                        className="ml-auto rounded bg-zinc-600 px-[0.7rem] py-[0.35rem] text-[0.7rem] leading-[1.05rem] text-white hover:bg-zinc-700 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-zinc-500"
                    >
                        로그아웃
                    </button>
                ) : (
                    <Link
                        href="/Ch07GlobalState/Exam02AuthContext/LoginComponent"
                        className="ml-auto rounded bg-green-600 px-[0.7rem] py-[0.35rem] text-[0.7rem] leading-[1.05rem] text-white hover:bg-green-700 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-600"
                    >
                        로그인
                    </Link>
                )}
        </div>
    );
}
