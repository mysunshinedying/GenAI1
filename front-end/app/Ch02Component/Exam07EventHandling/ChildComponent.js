export default function ChildComponent() {

    function handleClick1() {
        console.log('버튼1을 클릭함')
    }

    function handleClick3(name) {
        console.log(`${name} 클릭함`)
    }

    return (
        <section className="m-6 overflow-hidden rounded-lg border border-gray-200 bg-white shadow-sm">
            <header className="border-b border-gray-200 bg-gray-50 px-5 py-3">
                <h1 className="text-lg font-semibold">ChildComponents</h1>
            </header>
            <div className="p-5">
                야옹
            </div>
        </section>
    );
}