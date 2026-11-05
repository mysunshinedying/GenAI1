export default function ChildComponent(
    {
        name,
        version = 18,
        data,
        children,
    }
) {


    return (
        <article className="rounded-lg border border-indigo-100 bg-indigo-50 p-4">
            <h2 className="text-base font-semibold text-slate-900">자식 컴포넌트</h2>
            <p>name: {name}</p>
            <p>version: {version}</p>
            <p>
                {children}
            </p>
        </article>
    );
}
