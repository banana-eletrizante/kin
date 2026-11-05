import Link from "next/link";

const circles = [
  { id: "1", name: "Close Friends", members: 8, description: "The people I actually talk to." },
  { id: "2", name: "Family", members: 5, description: "Sunday calls and shared memories." },
  { id: "3", name: "Work Crew", members: 6, description: "The ones who get it." },
];

export default function CirclesPage() {
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
        <div className="mb-8 flex items-center justify-between">
          <h1 className="text-2xl font-medium">Your Circles</h1>
          <button className="rounded-full bg-neutral-900 px-4 py-2 text-sm text-white">
            New Circle
          </button>
        </div>

        <div className="space-y-3">
          {circles.map((circle) => (
            <div
              key={circle.id}
              className="flex items-center justify-between rounded-2xl border border-neutral-200 bg-white p-5"
            >
              <div>
                <div className="font-medium">{circle.name}</div>
                <div className="mt-1 text-sm text-neutral-500">
                  {circle.members} people · {circle.description}
                </div>
              </div>
              <button className="text-sm text-neutral-600 hover:text-neutral-900">
                Open
              </button>
            </div>
          ))}
        </div>
      </main>
    </div>
  );
}
