import Image from "next/image";
function fun() {
    return 'fun()의 반환값'
}

export default function Exam02Expressions() {
    const name = '리액트'
    const imageFile = 'photo1.jpg'

    return (
        <section className="m-6 overflow-hidden rounded-lg border border-gray-200 bg-white shadow-sm">
            <header className="border-b border-gray-200 bg-gray-50 px-5 py-3">
                <h1 className="text-lg font-semibold">Exam02Expressions</h1>
            </header>
            <div className="p-5">
                <p>{name}</p>
                <p>{fun()}</p>
                <p>
                    <Image alt="이미지" src={`/images/${imageFile}`} width={705} height={444} style={{width:'200px', height: 'auto'}}/>
                    {/*<Image alt="이미지" src={`/images/${imageFile}`} width="200" height="130"/>*/}
                </p>
            </div>
        </section>
    )
}