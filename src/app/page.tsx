"use client";

import { useState } from "react";
import Link from "next/link";

const mockCircles = [
  { id: "1", name: "Close Friends", members: 8, posts: 24 },
  { id: "2", name: "Family", members: 5, posts: 12 },
  { id: "3", name: "Work Crew", members: 6, posts: 18 },
];

const mockPosts = [
  {
    id: "1",
    circle: "Close Friends",
    author: "Maya",
    avatar: "M",
    content: "Finally finished that book we talked about. The ending hit different.",
    image: null,
    likes: 4,
    comments: 2,
    time: "2h ago",
  },
  {
    id: "2",
    circle: "Family",
    author: "You",
    avatar: "A",
    content: "Sunday lunch was perfect. These are the days I want to remember.",
    image: "/placeholder.jpg",
    likes: 7,
    comments: 3,
    time: "5h ago",
  },
  {
    id: "3",
    circle: "Close Friends",
    author: "Leo",
    avatar: "L",
    content: "Just a quiet walk. No big moment, just being here.",
    image: null,
    likes: 2,
    comments: 1,
    time: "1d ago",
  },
];

export default function Home() {
  const [activeCircle, setActiveCircle] = useState("all");

  const filteredPosts = activeCircle === "all"
    ? mockPosts
    : mockPosts.filter((p) => p.circle === activeCircle);

  return (
    <div className="min-h-screen">
      {/* Header */}
      <header className="border-b border-neutral-200 bg-white">
        <div className="mx-auto flex max-w-2xl items-center justify-between px-4 py-4">
          <Link href="/" className="text-2xl font-semibold tracking-tight">
            KIN
          </Link>
          <div className="flex items-center gap-4 text-sm">
            <Link href="/circles" className="text-neutral-600 hover:text-neutral-900">
              Circles
            </Link>
            <Link href="/profile" className="text-neutral-600 hover:text-neutral-900">
              Profile
            </Link>
          </div>
        </div>
      </header>

      <main className="mx-auto max-w-2xl px-4 py-8">
        {/* Hero */}
        <div className="mb-10 text-center">
          <h1 className="text-3xl font-medium tracking-tight text-neutral-900">
            Post without fear.
          </h1>
          <p className="mt-2 text-neutral-500">
            A calm space for the people who actually matter.
          </p>
        </div>

        {/* Circle filter */}
        <div className="mb-8 flex gap-2 overflow-x-auto pb-2">
          <button
            onClick={() => setActiveCircle("all")}
            className={`whitespace-nowrap rounded-full px-4 py-1.5 text-sm transition ${
              activeCircle === "all"
                ? "bg-neutral-900 text-white"
                : "bg-white text-neutral-600 border border-neutral-200 hover:bg-neutral-100"
            }`}
          >
            All circles
          </button>
          {mockCircles.map((circle) => (
            <button
              key={circle.id}
              onClick={() => setActiveCircle(circle.name)}
              className={`whitespace-nowrap rounded-full px-4 py-1.5 text-sm transition ${
                activeCircle === circle.name
                  ? "bg-neutral-900 text-white"
                  : "bg-white text-neutral-600 border border-neutral-200 hover:bg-neutral-100"
              }`}
            >
              {circle.name}
            </button>
          ))}
        </div>

        {/* Feed */}
        <div className="space-y-6">
          {filteredPosts.map((post) => (
            <article
              key={post.id}
              className="rounded-2xl border border-neutral-200 bg-white p-5 shadow-sm"
            >
              <div className="flex items-center gap-3">
                <div className="flex h-9 w-9 items-center justify-center rounded-full bg-neutral-100 text-sm font-medium">
                  {post.avatar}
                </div>
                <div>
                  <div className="font-medium">{post.author}</div>
                  <div className="text-xs text-neutral-500">
                    {post.circle} · {post.time}
                  </div>
                </div>
              </div>

              <p className="mt-4 text-[15px] leading-relaxed text-neutral-800">
                {post.content}
              </p>

              {post.image && (
                <div className="mt-4 aspect-[4/3] overflow-hidden rounded-xl bg-neutral-100" />
              )}

              <div className="mt-4 flex items-center gap-5 text-sm text-neutral-500">
                <button className="flex items-center gap-1.5 hover:text-neutral-900">
                  <span className="text-base">♡</span>
                  <span>{post.likes}</span>
                </button>
                <button className="flex items-center gap-1.5 hover:text-neutral-900">
                  <span className="text-base">💬</span>
                  <span>{post.comments}</span>
                </button>
              </div>
            </article>
          ))}
        </div>

        {filteredPosts.length === 0 && (
          <div className="py-20 text-center text-neutral-500">
            Nothing here yet. Post something real.
          </div>
        )}
      </main>
    </div>
  );
}
