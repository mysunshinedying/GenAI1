"use client";

import { useState } from "react";

export default function Exam04FormState() {
    const [submitted, setSubmitted] = useState(false);
    const [form, setForm] = useState({
        name: "",
        email: ""
    })

    const handleChange = (event) => {
        if(event.target.name === "name") {
            setForm({...form, name: event.target.value});
        } else if(event.target.name === "email") {
            setForm({...form, email: event.target.value});
        }
    };

    const handleClick = (event) => {
        event.preventDefault();
        console.log("Form submitted:", form);        
        setSubmitted(true);
    };

    return (
        <section className="w-full rounded-lg border border-zinc-200 bg-white text-zinc-900 shadow-sm dark:border-zinc-700 dark:bg-zinc-900 dark:text-zinc-100">
            <header className="rounded-t-lg border-b border-zinc-200 bg-zinc-100 px-4 py-1 dark:border-zinc-700 dark:bg-zinc-800">
                <h2 className="font-semibold">Exam04FormState</h2>
            </header>
            <div className="space-y-4 p-4">
                <form
                    className="max-w-sm space-y-3"
                    onSubmit={(event) => {
                        event.preventDefault();
                        setSubmitted(true);
                    }}
                >
                    <label className="block text-sm font-medium">
                        이름
                        <input
                            type="text"
                            name="name"
                            autoComplete="name"
                            required
                            value={form.name}
                            onChange={handleChange}
                            className="mt-1 block w-full rounded border border-zinc-300 px-3 py-2 focus:outline-2 focus:outline-emerald-600 dark:border-zinc-600 dark:bg-zinc-800"
                        />
                    </label>
                    <label className="block text-sm font-medium">
                        이메일
                        <input
                            type="email"
                            name="email"
                            autoComplete="email"
                            required
                            value={form.email}
                            onChange={handleChange}
                            className="mt-1 block w-full rounded border border-zinc-300 px-3 py-2 focus:outline-2 focus:outline-emerald-600 dark:border-zinc-600 dark:bg-zinc-800"
                        />
                    </label>
                    <button
                        type="submit"
                        onClick = {handleClick}
                        className="rounded-md bg-emerald-600 px-3 py-1.5 text-xs font-semibold text-white hover:bg-emerald-700 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-emerald-600"
                    >
                        제출
                    </button>
                </form>
                <div aria-live="polite">
                    {submitted && (
                        <div className="rounded bg-zinc-100 p-3 text-sm dark:bg-zinc-800">
                            <p>
                                이름: {form.name}
                            </p>
                            <p>
                                이메일: {form.email}
                            </p>
                        </div>
                    )}
                </div>
            </div>
        </section>
    );
}
