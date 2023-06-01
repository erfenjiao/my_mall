<template>
  <el-dialog
    :title="!dataForm.attrGroupId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible"
    @closed="dialogClose" >
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="组名" prop="attrGroupName">
      <el-input v-model="dataForm.attrGroupName" placeholder="组名"></el-input>
    </el-form-item>
    <el-form-item label="排序" prop="sort">
      <el-input v-model="dataForm.sort" placeholder="排序"></el-input>
    </el-form-item>
    <el-form-item label="描述" prop="descript">
      <el-input v-model="dataForm.descript" placeholder="描述"></el-input>
    </el-form-item>
    <el-form-item label="组图标" prop="icon">
      <el-input v-model="dataForm.icon" placeholder="组图标"></el-input>
    </el-form-item>
    <el-form-item label="所属分类id" prop="catelogId">
      <!-- <el-input v-model="dataForm.catelogId" placeholder="所属分类id"></el-input> -->
      <el-cascader placeholder="试试搜索：手机" filterable v-model="dataForm.catelogPath" :options="categorys" :props="props"></el-cascader>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        props:{
        value:"catId",
        label:"name",
        children:"children"
      },
      categorys:[],
        visible: false,
        dataForm: {
          attrGroupId: 0,
          attrGroupName: '',
          sort: '',
          descript: '',
          icon: '',
          catelogPath :[],
          catelogId: 0 //保存要提交的子节点的id
        },
        dataRule: {
          attrGroupName: [
            { required: true, message: '组名不能为空', trigger: 'blur' }
          ],
          sort: [
            { required: true, message: '排序不能为空', trigger: 'blur' }
          ],
          descript: [
            { required: true, message: '描述不能为空', trigger: 'blur' }
          ],
          icon: [
            { required: true, message: '组图标不能为空', trigger: 'blur' }
          ],
          catelogId: [
            { required: true, message: '所属分类id不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.attrGroupId = id || 0  //接收id，没有就用 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          // 如果 attrGroupId 有值，就用其检索详细的信息
          if (this.dataForm.attrGroupId) {
            this.$http({
              url: this.$http.adornUrl(`/product/attrgroup/info/${this.dataForm.attrGroupId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                // 将所有数据填充到 dataFrom 中
                this.dataForm.attrGroupName = data.attrGroup.attrGroupName
                this.dataForm.sort = data.attrGroup.sort
                this.dataForm.descript = data.attrGroup.descript
                this.dataForm.icon = data.attrGroup.icon
                this.dataForm.catelogId = data.attrGroup.catelogId
                // 查出 catelogId 的完整路径  后端AttrGroupEntity新增完整路径属性
                this.dataForm.catelogPath = data.attrGroup.catelogPath;

              }
            })
          }
        })
      },
      dialogClose(){
          this.dataForm.catelogPath = [];
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/product/attrgroup/${!this.dataForm.attrGroupId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'attrGroupId': this.dataForm.attrGroupId || undefined,
                'attrGroupName': this.dataForm.attrGroupName,
                'sort': this.dataForm.sort,
                'descript': this.dataForm.descript,
                'icon': this.dataForm.icon,
                 catelogId: this.dataForm.catelogPath[this.dataForm.catelogPath.length - 1],
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    // 子组件attrgroup-add-or-update给父组件attrgroup发送事件
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      },
      getCategorys() {
      this.$http({
        url: this.$http.adornUrl("/product/category/list/tree"),
        method: "get",
      }).then(({ data }) => {
        console.log("成功了获取到菜单数据....", data.data);
        this.categorys = data.data;
      });
    }
    },
    created(){
      this.getCategorys();
    }        
  }
</script>
