import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "KIN — The place where you post without fear",
  description: "A calm, private social space for real connections. Circles, chronological feed, no addictive metrics.",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body className={`${inter.className} bg-neutral-50 text-neutral-900 antialiased`}>
        {children}
      </body>
    </html>
  );
}
