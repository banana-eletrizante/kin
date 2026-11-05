import Link from "next/link";

export default function ProfilePage() {
  return (
    <div className="min-h-screen">
      <header className="border-b border-neutral-200 bg-white">
        <div className="mx-auto flex max-w-2xl items-center justify-between px-4 py-4">
          <Link href="/" className="text-2xl font-semibold tracking-tight">
            KIN
          </Link>
          <Link href="/" className="text-sm text-neutral-600 hover:text-neutral-900">
            ← Feed
          </Link>
        </div>
      </header>

      <main className="mx-auto max-w-2xl px-4 py-10">
        <div className="text-center">
          <div className="mx-auto flex h-20 w-20 items-center justify-center rounded-full bg-neutral-100 text-2xl font-medium">
            A
          </div>
          <h1 className="mt-4 text-2xl font-medium">André</h1>
          <p className="mt-1 text-neutral-500">Posting without fear.</p>
        </div>

        <div className="mt-10 grid grid-cols-3 gap-4 text-center">
          <div className="rounded-2xl border border-neutral-200 bg-white p-4">
            <div className="text-xl font-medium">3</div>
            <div className="text-xs text-neutral-500">Circles</div>
          </div>
          <div className="rounded-2xl border border-neutral-200 bg-white p-4">
            <div className="text-xl font-medium">12</div>
            <div className="text-xs text-neutral-500">Posts</div>
          </div>
          <div className="rounded-2xl border border-neutral-200 bg-white p-4">
            <div className="text-xl font-medium">19</div>
            <div className="text-xs text-neutral-500">Connections</div>
          </div>
        </div>
      </main>
    </div>
  );
}
