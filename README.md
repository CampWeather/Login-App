# Login App (MVVM + Room + Jetpack Compose)

Aplikasi login sederhana: input username & password, tombol login, validasi,
pesan sukses/gagal, dan data user disimpan di Room Database (`login_db`, tabel `users`).

## Cara menjalankan
1. Ekstrak zip, lalu di Android Studio pilih **File > Open** dan pilih folder `LoginApp`.
2. Tunggu Gradle Sync selesai (butuh internet untuk unduh dependency).
3. Jalankan di emulator / HP (minSdk 24).

## Akun dummy
| Username | Password |
|----------|----------|
| admin    | 12345    |

Disisipkan otomatis saat aplikasi pertama kali dibuka (jika tabel masih kosong).

## Skenario uji
| Input                | Hasil                                   |
|----------------------|-----------------------------------------|
| admin / 12345        | Login Berhasil – Selamat datang, admin! |
| admin / salah        | Username atau Password Salah            |
| userxxx / 12345      | Username atau Password Salah            |
| (kosong)             | Username dan password wajib diisi       |

## Struktur folder
```
com.example.loginapp
├── data
│   ├── local
│   │   ├── dao/UserDao.kt
│   │   ├── database/AppDatabase.kt
│   │   └── entity/User.kt
│   └── repository/UserRepository.kt
├── ui/screen/LoginScreen.kt
├── viewmodel/LoginViewModel.kt
└── MainActivity.kt
```

## Alur login
LoginScreen (Compose) → LoginViewModel.login() → UserRepository.login()
→ UserDao.login() → Room Database → SQLite

Cek isi database: **View > Tool Windows > App Inspection > Database Inspector**.

> Catatan: password disimpan plaintext hanya untuk pembelajaran.
> Pada aplikasi nyata, gunakan hashing (mis. BCrypt / Argon2).
