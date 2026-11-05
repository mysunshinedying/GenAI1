'use client';
import Link from "next/link";
import {useContext} from "react";
import {AuthContext} from "@/contexts/AuthContext";
import {redirect} from "next/navigation";

export default function Exam02AuthContext() {

    const authContext = useContext(AuthContext);

    return (
        <section className="m-6 overflow-hidden rounded-lg border border-gray-200 bg-white shadow-sm">
            <header className="border-b border-gray-200 bg-gray-50 px-5 py-3">
                <h1 className="text-lg font-semibold">Exam02AuthContext</h1>
            </header>
            <div className="p-5">
                {!authContext.isInitialized ?
                    (
                        <span
                            aria-hidden="true"
                            className="ml-auto h-7 w-16 shrink-0"
                        />
                    )
                    : authContext.memberId ? (
                        <button
                            onClick={
                                () => {
                                    authContext.setMemberId("");
                                    authContext.setAccessToken("");
                                    localStorage.removeItem("memberId");
                                    localStorage.removeItem("accessToken");
                                    redirect("/Ch07GlobalState/Exam02AuthContext/LoginComponent");
                                }
                            }
                            className="inline-flex items-center rounded-lg bg-zinc-600 px-5 py-2.5 text-sm font-semibold text-white shadow-sm transition hover:bg-zinc-700 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-zinc-600"
                        >
                            로그아웃
                        </button>
                    ) : (
                        <Link
                            href="/Ch07GlobalState/Exam02AuthContext/LoginComponent"
                            className="inline-flex items-center rounded-lg bg-green-600 px-5 py-2.5 text-sm font-semibold text-white shadow-sm transition hover:bg-green-700 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-600"
                        >
                            로그인
                        </Link>
                    )}
            </div>
        </section>
    );
}
