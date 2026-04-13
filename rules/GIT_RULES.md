# 🚀 Git & GitHub Workflow Guidelines

Tài liệu này quy định các nguyên tắc làm việc với Git để đảm bảo lịch sử commit sạch sẽ và quy trình phát triển ổn định.

---

## 🌿 1. Quy tắc đặt tên Branch (Branch Naming)

Tên branch phải bắt đầu bằng một prefix mô tả mục đích của thay đổi, theo sau là dấu gạch chéo `/` và mô tả ngắn gọn (viết thường, ngăn cách bằng dấu gạch ngang `-`).

**Cấu trúc:**

prefix/short-description


| Prefix   | Ý nghĩa                                      | Ví dụ                  |
|----------|---------------------------------------------|------------------------|
| feat     | Phát triển tính năng mới                    | feat/login-page        |
| fix      | Sửa lỗi (bug fix)                           | fix/header-responsive  |
| docs     | Cập nhật tài liệu                           | docs/update-readme     |
| refactor | Tối ưu hóa code nhưng không đổi tính năng   | refactor/api-call      |
| chore    | Thay đổi nhỏ về build, tool, thư viện       | chore/add-dotenv       |

---

## 📝 2. Quy tắc Commit Message

Sử dụng format **Conventional Commits** để lịch sử commit dễ đọc và có thể tự động hóa.

**Cấu trúc:**
``
    <type>(<scope>): <description>
``

type: description

- `type`: giống với prefix của branch (feat, fix, docs,...)
- `scope`: nếu có ticket, issue như #number thì ghi vào
- `description`:
  - Viết ngắn gọn
  - Không viết hoa chữ cái đầu
  - Không dấu chấm ở cuối

**Ví dụ:**

feat(ticket 678): add facebook login button
fix(issue 798): resolve memory leak in dashboard


---

## 📏 3. Giới hạn số lượng Commit

Để giữ cho lịch sử nhánh (branch history) gọn gàng trước khi merge:

- Tối đa **3 commit** cho mỗi branch
- Nếu có nhiều commit nhỏ (ví dụ: "fix typo", "update again"), cần **gộp (squash)** lại trước khi tạo Pull Request

**Lệnh gợi ý:**
```bash
git rebase -i HEAD~<so_luong_commit>
```

---
🔄 4. Quy trình Merge & Pull Request (PR)

❌ Không được push trực tiếp vào develop hoặc main
- Tạo Pull Request
- Sau khi hoàn thành task:
    Push code lên GitHub
    Tạo PR vào branch develop
    Code Review
    Mỗi PR cần ít nhất 1 người duyệt (Approve)
    Nếu bị Request changes → phải sửa lại code
    Merge
- Sau khi được duyệt:
    Sử dụng Squash and merge
    Gộp toàn bộ commit thành 1 commit duy nhất

---

🛠 5. Quy trình làm việc tiêu chuẩn

# Luôn bắt đầu từ develop mới nhất
git checkout develop
git pull origin develop

# Tạo branch mới
git checkout -b feat/my-awesome-feature

# Làm việc và commit (tối đa 3 commit)

# Push lên remote
git push origin feat/my-awesome-feature
Sau đó:
Tạo Pull Request trên GitHub
Đợi leader / đồng nghiệp review & approve