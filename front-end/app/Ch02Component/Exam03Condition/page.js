export default function Exam03Condition() {
    const var1 = '리액트'

    return (
        <>
            <section className="mx-6 mt-6 mb-12 overflow-hidden rounded-lg border border-gray-200 bg-white shadow-sm">
                <header className="border-b border-gray-200 bg-gray-50 px-5 py-3">
                    <h1 className="text-lg font-semibold">Exam03Condition</h1>
                </header>
                <div className="p-5">
                    <div>{(var1 === '리액트') ? <p>true</p> : <p>false</p> }</div>
                    <div>
                        <div>{(var1 === '리액트') && <p>true(&&)</p>}</div>
                        <div>{(var1 !== '리액트') && <p>false(&&)</p>}</div>
                        <div>{(var1 === '리액트') || <p>true(||)</p>}</div>
                        <div>{(var1 !== '리액트') || <p>false(||)</p>}</div>
                    </div>
                </div>
            </section>
        </>
    )
}